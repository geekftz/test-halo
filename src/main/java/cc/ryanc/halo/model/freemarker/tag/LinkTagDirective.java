package cc.ryanc.halo.model.freemarker.tag;

import cc.ryanc.halo.service.LinkService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Freemarker custom tag of link.
 *
 * @author : RYAN0UP
 * @date : 2019/3/22
 */
@Component
public class LinkTagDirective implements TemplateDirectiveModel {

    private static final String METHOD_KEY = "method";

    private final LinkService linkService;

    public LinkTagDirective(LinkService linkService) {
        this.linkService = linkService;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        final DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);

        if (params.containsKey(METHOD_KEY)) {
            String method = params.get(METHOD_KEY).toString();
            switch (method) {
                case "list":
                    env.setVariable("links", builder.build().wrap(linkService.listAll()));
                    break;
                case "listTeam":
                    env.setVariable("links", builder.build().wrap(null));
                    break;
                case "count":
                    env.setVariable("count", builder.build().wrap(linkService.count()));
                default:
                    break;
            }
        }
        body.render(env.getOut());
    }
}