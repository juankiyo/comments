package com.juan.nunez.mapping;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CommentMapperTest {

    private CommentMapper commentMapper;
    @Mock
    private MapperFactory mockMapperFactory;
    @Mock
    private ClassMapBuilder mockClassMapBuilder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        commentMapper = new CommentMapper();
    }

    @Test
    public void constructorShouldCreateCommentMapper() {
        assertNotNull(new CommentMapper());
    }

    @Test
    public void configureShouldCallMapperFactoryClassMap() {
        when(mockMapperFactory.classMap(any(Class.class), any(Class.class))).thenReturn(mockClassMapBuilder);
        when(mockClassMapBuilder.byDefault()).thenReturn(mockClassMapBuilder);
        doNothing().when(mockClassMapBuilder).register();

        commentMapper.configure(mockMapperFactory);

        verify(mockMapperFactory).classMap(any(Class.class), any(Class.class));
        verify(mockClassMapBuilder).byDefault();
        verify(mockClassMapBuilder).register();
    }
}
