package com.hktvmall.ittask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hktvmall.ittask.entity.CSV;
public interface CSVRepository extends JpaRepository<CSV,String>{

    public CSV findById (String Id);
}

