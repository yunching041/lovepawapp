// SecondTabFragment.java
package com.example.lovepaw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SecondTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_secondpersonal, container, false);

        // 获取 Spinner 对象
        Spinner timeSpinner = rootView.findViewById(R.id.time1);

        // 准备数据源
        List<String> timeList = new ArrayList<>();
        timeList.add("早上");
        timeList.add("中午");
        timeList.add("下午");

        // 创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, timeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 设置适配器
        timeSpinner.setAdapter(adapter);

        // 设置选中监听器
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // 处理选择项的逻辑
                String selectedTime = timeList.get(position);
                Toast.makeText(requireContext(), "选择的时间：" + selectedTime, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // 处理未选择任何项的逻辑
            }
        });

        return rootView;
    }
}

