# stockscore
realtime calc the score for every stock
实时计算提取股票的综合分（double，0-100之间取小数点后一位），
第一期先计算LongScore（长期投资），基于过去7年历史数据，对未来一年做计划
考虑的细节变量有：（double，0-100之间取小数点后一位）
     个股变量：个股的财务、趋势、安全边际、活跃、事件
     行业变量：所属行业的财务、安全边际、趋势、活跃、事件，一般一个股票只属于一个行业，所以这里只考虑一个行业
     概念变量：所属概念的财务、安全边际、趋势、活跃、事件，可以有多个概念，每个概念可以给个权重系数；
     市场变量：市场的财务、安全边际、趋势、活跃、事件，6打头的以上证指数、0打头的以深成指为准，3打头的以创业板指数为准     
最后得出一个综合分
         
下面是各个需要考虑的变量：
   财务分：市净率（近一年、近三年、近七年）、每股盈利和市盈率（近一年、近三年（最大、最小、每年平均）、近七年（最大、最小、每年平均））、负债率、相对负债率（自己的负债率除以行业的平均负债率）、现金流入系数、持有现金、短期债务比重
   安全分：包括价格安全（跌破幅度离最大跌幅的距离）、跌破天数、近一年跌破天数相对一年前的跌破天数占比、跌破次数（3天内连续多次跌破只能算1次），近一年跌破次数与一年前的跌破次数占比
   趋势分：金叉分数在50以上，死叉分数在50以下，包括10日上下穿30日（短期）、10日穿120日（中期）、10日穿250日（长期）
   活跃分: 考虑成交量、量比，行业、板块、市场考虑整个市场（板块）的涨跌个股的比重
   事件分：按事件好坏、大小、远近打分，比较难，可能需要人工评判
   
总体思路： 变量 --> 系数 --> 分数， 为隐藏细节，变量不显示，只展示系数和分数
   
   
          