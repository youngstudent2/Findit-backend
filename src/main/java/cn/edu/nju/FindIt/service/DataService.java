package cn.edu.nju.FindIt.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DataService {
    ResponseEntity<List<Map<String, String>>> todayData();
}
