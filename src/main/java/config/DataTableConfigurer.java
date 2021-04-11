package config;
import java.util.Locale;
import java.util.Map;

import apiEngine.model.Comment;
import apiEngine.model.Post;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

//Can be used from runnner
@Deprecated
public class DataTableConfigurer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(Post.class, new TableEntryTransformer<Post>() {
            @Override
            public Post transform(Map<String, String> entry) {
                return new Post(Integer.parseInt(entry.get("userId")),
                        Integer.parseInt(entry.get("id")),
                        entry.get("title"),
                        entry.get("body"));
            }
        }));

        registry.defineDataTableType(new DataTableType(Comment.class, new TableEntryTransformer<Comment>() {
            @Override
            public Comment transform(Map<String, String> entry) {
                return new Comment(Integer.parseInt(entry.get("postId")),
                        Integer.parseInt(entry.get("id")),
                        entry.get("name"),
                        entry.get("email"),
                        entry.get("body"));
            }
        }));
    }

}