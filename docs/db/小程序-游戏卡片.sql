INSERT INTO mina_game_card (title, logo_url,
                            game_url,
                            remark, tags, sort, created_at, creator, last_updated_at,
                            last_updater, enabled)
VALUES ('测试游戏',
        'http://cdn.hocgin.top/fontapplogo.png',
        'http://cdn.hocgin.top/InterglacticTransmissing.nes',
        '草鸡管理员的测试游戏', '游戏',
        DEFAULT, now(), 0, null, null, DEFAULT);
