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

        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");

        get("/", (request, response) -> {
            DataBase base = new DataBase();
            //base.createTable();
                   base.addTask(new Task(1, "Заголовок", "Контент",false));
                   //DataBase.addTask(new Task(2, "Заголовок1", "Контент1",true));
                   //DataBase.addTask(new Task(3, "Заголовок2", "Контент2", true));
            //Map<String, Object> root = new HashMap<String, Object>();
            //root.put("taskes", DataBase.fetchCustomers(1));
            StringWriter writer = new StringWriter();
            //try {
                //Template formTemplate = configuration.getTemplate("templates/main.ftl");
                 //formTemplate.process(root, writer);
             //} catch (Exception e) {
               //halt(500);
            //}
            System.out.println("VERY GOOD!");
            return writer;
        });

        get("/addpage/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                int id = Integer.parseInt(request.params("id"));
                String check,title,content;
                if(id == 0) {
                    check = "Add";
                    title = "Title";
                    content = "Content";
                }
                else {
                    check = "Edit";
                    //Метод возвращающий одну задачу из бд
                    //Заполнение title и content
                    title = "1";//Изменить
                    content = "2";//Изменить
                }
                Template resultTemplate = configuration.getTemplate("templates/addpage.ftl");

                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("title", title);
                map.put("content", content);
                map.put("check", check);

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
                response.redirect("/");
            } catch (Exception e) {
                halt(500);
            }
            return writer;
        });

        post("/add/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            int id = Integer.parseInt(request.params("id"));
            try {
                String title = request.queryParams("title") != null ? request.queryParams("title") : "Title";
                String content = request.queryParams("content") != null ? request.queryParams("content") : "Content";
                //if(...){проверка на существование такой записи в бд
                //Вызов метода редактирования
                //}
                //else{вызов метода добавления}
                response.redirect("/");
            } catch (Exception e) {
                halt(500);
            }

            return writer;
        });

    }

}
