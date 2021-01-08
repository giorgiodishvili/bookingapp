package com.booking.book.dao;

import com.booking.book.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "roomCategory", path = "room-category")
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {

}
