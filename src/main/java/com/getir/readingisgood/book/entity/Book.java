package com.getir.readingisgood.book.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends AbstractEntity {
}
