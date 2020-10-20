package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by WF on 2020/10/19 9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
        private long total;     //总记录数
        private List<T> rows;   //每页的结果集
}
