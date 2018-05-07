import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import javax.xml.crypto.Data;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

public class Main {
    public static void main(String args[]) {

        DataBase base = new DataBase();
        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");

        get("/", (request, response) -> {

            base.createTable();
                   //base.addTask(new Task(1, "Заголовок", "Контент",false));
                   //base.addTask(new Task(2, "Заголовок1", "Контент1",true));
                   //base.addTask(new Task(3, "Заголовок2", "Контент2", true));
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("taskes", base.getAllTasks());
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/main.ftl");
                 formTemplate.process(root, writer);
             } catch (Exception e) {
              halt(500);
           }
            System.out.println("VERY GOOD!");
            return writer;
        });

        get("/addpage/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                int id = Integer.parseInt(request.params("id"));

                String check,title,content;
                String relize;
                if(id == 0) {
                    relize = "0";
                    check = "Add";
                    title = "Title";
                    content = "Content";
                }
                else {
                    relize = "1";
                    check = "Edit";
                    //Метод возвращающий одну задачу из бд
                    //Заполнение title и content
                    List list = base.fetchCustomers(id);
                    Task t = (Task)list.get(0);
                    title = t.getTitle();//Изменить
                    content = t.getContent();//Изменить
                }
                Template resultTemplate = configuration.getTemplate("templates/addpage.ftl");

                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("title", title);
                map.put("content", content);
                map.put("check", check);
                map.put("relize", relize);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                halt(500);
            }

            return writer;
        });

        get("/delete/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            int id = Integer.parseInt(request.params("id"));
            try {
                String title = request.queryParams("title") != null ? request.queryParams("title") : "Title";
                String content = request.queryParams("content") != null ? request.queryParams("content") : "Content";

                //Вызов метода удаления
                base.delete(id);
                response.redirect("/");
            } catch (Exception e) {
                halt(500);
            }
            return writer;
        });

        get("/ready/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            int id = Integer.parseInt(request.params("id"));
            try {
                //Вызов метода изменения статуса записи

                List list = base.fetchCustomers(id);
                Task t = (Task)list.get(0);
                boolean x = t.isStatus();
                t.setStatus(!x);
                base.editTask(t);
                response.redirect("/");
            } catch (Exception e) {
                halt(500);
            }
            return writer;
        });

        post("/add/:id/:relize", (request, response) -> {
            StringWriter writer = new StringWriter();
            int id = Integer.parseInt(request.params("id"));
            int relize = Integer.parseInt(request.params("relize"));
            try {
                String title = request.queryParams("title") != null ? request.queryParams("title") : "Title";
                String content = request.queryParams("content") != null ? request.queryParams("content") : "Content";
                if(relize == 1)
                    {
                    List list = base.fetchCustomers(id);
                    Task t = (Task)list.get(0);
                    t.setTitle(title);
                    t.setContent(content);
                        base.editTask(t);
                    }
                else
                    {
                       int regenId = 5 + (int) (Math.random() * 400);
                       base.addTask(new Task(regenId,title,content,false));
                    }

                response.redirect("/");
            } catch (Exception e) {
                halt(500);
            }

            return writer;
        });

    }

}
