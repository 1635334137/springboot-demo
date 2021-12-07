package com.lanzong.mapper;

import com.lanzong.pojo.BookMybatis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 除了在接口上注解@Mapper扫描外，还有另一种方式
 * 在配置类上添加@MapperScan("包名"),表示扫描该包下的所有接口作为Mapper
 */
@Mapper
public interface BookMybatisMapper {
    int addBookMybatis(BookMybatis bookMybatis);
    int deleteBookMybatis(Integer id);
    int updateBookMybatis(BookMybatis bookMybatis);
    BookMybatis getBookMybatisById(Integer id);
    List<BookMybatis> getAllBookMybatis();
}
