package com.dpcoi.drived.service;/**
 * Created by liangzhifu
 * DATE:2017/7/3.
 */

import com.dpcoi.rr.query.RRProblemQuery;

/**
 *
 * @author lzf
 * @create 2017-07-03
 **/

public interface ExportExcelService {

    /**
     * 导出RR问题点EXCEL
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public String excelRRProblem(String path, RRProblemQuery rrProblemQuery) throws Exception;

}
