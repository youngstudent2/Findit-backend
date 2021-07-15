package cn.edu.nju.FindIt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cn.edu.nju.FindIt.repository.ItemRepository;
import cn.edu.nju.FindIt.repository.UserRepository;

@Service
public class DataServiceImpl implements DataService{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataServiceImpl(ItemRepository itemRepository, UserRepository userRepository){
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<Map<String, String>>> todayData() {
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();

        Map<String, String> todayLost = new HashMap<String, String>();
        todayLost.put("type", "今日丢失");
        todayLost.put("value", itemRepository.todayLostCount().toString());
        ret.add(todayLost);

        Map<String, String> todayFind = new HashMap<String, String>();
        todayFind.put("type", "今日招领");
        todayFind.put("value", itemRepository.todayFindCount().toString());
        ret.add(todayFind);

        Map<String, String> totalLost = new HashMap<String, String>();
        totalLost.put("type", "总共丢失");
        totalLost.put("value", itemRepository.totalLostCount().toString());
        ret.add(totalLost);

        Map<String, String> totalFind = new HashMap<String, String>();
        totalFind.put("type", "总共招领");
        totalFind.put("value", itemRepository.totalFindCount().toString());
        ret.add(totalFind);

        Map<String, String> finishCount = new HashMap<String, String>();
        finishCount.put("type", "成功找回");
        finishCount.put("value", itemRepository.finishCount().toString());
        ret.add(finishCount);

        Map<String, String> userCount = new HashMap<String, String>();
        userCount.put("type", "用户总数");
        userCount.put("value", userRepository.userCount().toString());
        ret.add(userCount);

        return ResponseEntity.ok().body(ret);
    }
}
