package com.juan.nunez.mapping;

import com.juan.nunez.domain.Comment;
import com.juan.nunez.model.CommentDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;


@Component
public class CommentMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Comment.class, CommentDTO.class).byDefault().register();
    }
}
