import org.testng.annotations.DataProvider;

public class ConfigurationTest {

    @DataProvider(name = "resources")
    public static Object[][] resources() {
        return new Object[][] { {"posts"}, {"comments"}, {"albums"}, {"photos"}, {"todos"}, {"users"}};
    }

    @DataProvider(name = "resourcesSchema")
    public static Object[][] resourcesSchema() {
        return new Object[][] { {"posts", "posts.json"}, {"comments", "comments.json"}, {"albums", "albums.json"},
                                {"photos", "photos.json"}, {"todos", "todos.json"}, {"users", "users.json"}};
    }

    @DataProvider(name = "resourceBySpecificID")
    public static Object[][] resourceBySpecificID() {
        return new Object[][] { {1, "posts/{id}"}, {1, "comments/{id}"}};
    }

    @DataProvider(name = "compareNodesByID")
    public static Object[][] compareNodesByID() {
        return new Object[][] {
                {20, "posts/{id}", 2, "doloribus ad provident suscipit at", "qui consequuntur ducimus possimus quisquam amet similique\nsuscipit porro ipsam amet\neos veritatis officiis exercitationem vel fugit aut necessitatibus totam\nomnis rerum consequatur expedita quidem cumque explicabo"},
                {50, "posts/{id}", 5, "repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem", "error suscipit maxime adipisci consequuntur recusandae\nvoluptas eligendi et est et voluptates\nquia distinctio ab amet quaerat molestiae et vitae\nadipisci impedit sequi nesciunt quis consectetur"},
                {100, "posts/{id}", 10, "at nam consequatur ea labore ea harum", "cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut"}
        };
    }

}
