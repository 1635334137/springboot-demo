package com.lanzong.service;

import com.lanzong.mapper.BookMybatisMapper;
import com.lanzong.pojo.BookMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMybatisService {

    @Autowired
    BookMybatisMapper bookMapper;

    public int addBookMybatis(BookMybatis bookMybatis){
        return bookMapper.addBookMybatis(bookMybatis);
    }

    public int updateBookMybatis(BookMybatis bookMybatis){
        return bookMapper.updateBookMybatis(bookMybatis);
    }

    public int deleteBookMybatis(Integer id){
        return bookMapper.deleteBookMybatis(id);
    }

    public BookMybatis getBookMybatisById(Integer id){
        return bookMapper.getBookMybatisById(id);
    }

    public List<BookMybatis> getAllBookMybatis(){
        return bookMapper.getAllBookMybatis();
    }
}
