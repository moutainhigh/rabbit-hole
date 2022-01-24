
    // 发货事件
            .withChoice().source(TradeOrderStatus.WaitShip)
            .and()
    // 收货事件
            .withChoice().source(TradeOrderStatus.WaitReceipt)
            .first(TradeOrderStatus.PartReceived, null, null)
            .then(TradeOrderStatus.FullReceived, null, null)
            .and()
    // 取消事件
            .withChoice().source(TradeOrderStatus.WaitReceipt)
            .first(TradeOrderStatus.PartReceived, null, null)
            .then(TradeOrderStatus.FullReceived, null, null)
            .and()
    // 评价事件
            .withExternal().event(OrderEvent.Commented).source(TradeOrderStatus.WaitComment).target(TradeOrderStatus.Commented).and()
            .withJunction().and()
    // 加入状态：需要在两个状态和转换中定义连接才能正常工作
    // 多到一; [待发货、部分发货] -> 待收货
            .withJoin().and()
            .withExit().and()
            .withFork().and()
            .withEntry().and()
            .withHistory().and()
            .withInternal().and()
            .withLocal().and()
