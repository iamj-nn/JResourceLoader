package nn.iamj.resources.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public class Resource {

    private final String url;
    private final String prompt;
    private final boolean force;

}
