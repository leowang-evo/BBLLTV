package com.xx.blbl.ui.catalog;

import android.content.Context;

import com.xx.blbl.R;
import com.xx.blbl.model.series.*;

import java.util.List;

/** Application-only filter catalogue extracted from the original merged owner. */
final class SeriesFilterFactory {
    private final Context f530b;

    SeriesFilterFactory(Context context) {
        f530b = context;
    }

    static AllSeriesFilterOption option(String title, String value) {
        AllSeriesFilterOption result = new AllSeriesFilterOption();
        result.setTitle(title);
        result.setValue(value);
        return result;
    }

    public AllSeriesFilterModel f(int i4) {
        List<AllSeriesFilterOption> listD;
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_area);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.ic_earth);
        allSeriesFilterModel.setKey("area");
        if (i4 == SeriesTypeEnum.Anime.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("全部地区", "-1"),
                            option("日本", "2"),
                            option("美国", "3"),
                            option(
                                    "其他",
                                    "1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70"));
        } else {
            listD =
                    i4 == SeriesTypeEnum.Drama.getType()
                            ? java.util.Arrays.asList(
                                    option("全部地区", "-1"),
                                    option("中国", "1"),
                                    option("日本", "2"),
                                    option("美国", "3"),
                                    option("英国", "4"),
                                    option("泰国", "10"),
                                    option(
                                            "其他",
                                            "5,8,9,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70"))
                            : java.util.Arrays.asList(
                                    option("全部地区", "-1"),
                                    option("中国大陆", "1"),
                                    option("中国港台", "6,7"),
                                    option("美国", "3"),
                                    option("日本", "2"),
                                    option("韩国", "8"),
                                    option("法国", "9"),
                                    option("英国", "4"),
                                    option("德国", "15"),
                                    option("泰国", "10"),
                                    option("意大利", "35"),
                                    option("西班牙", "13"),
                                    option(
                                            "其他",
                                            "5,11,12,14,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70"));
        }
        allSeriesFilterModel.setOptions(listD);
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel g(int i4) {
        List<AllSeriesFilterOption> listD;
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_order);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.ic_sort);
        allSeriesFilterModel.setKey("order");
        if (i4 == SeriesTypeEnum.Anime.getType() || i4 == SeriesTypeEnum.ChinaAnime.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("追番人数", "3"),
                            option("最近更新", "0"),
                            option("最高评分", "4"),
                            option("播放数量", "2"),
                            option("开播时间", "5"));
        } else if (i4 == SeriesTypeEnum.Variety.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("播放数量", "2"),
                            option("最近更新", "0"),
                            option("最新上映", "6"),
                            option("最高评分", "4"),
                            option("弹幕数量", "1"));
        } else if (i4 == SeriesTypeEnum.Movie.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("播放数量", "2"),
                            option("最近更新", "0"),
                            option("最新上映", "6"),
                            option("最高评分", "4"));
        } else if (i4 == SeriesTypeEnum.Drama.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("播放数量", "2"),
                            option("最近更新", "0"),
                            option("弹幕数量", "1"),
                            option("追剧人数", "3"),
                            option("最高评分", "4"));
        } else if (i4 == SeriesTypeEnum.Documentary.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("播放数量", "2"),
                            option("最高评分", "4"),
                            option("最近更新", "0"),
                            option("最新上映", "6"),
                            option("弹幕数量", "1"));
        } else {
            AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
            allSeriesFilterOption.setTitle("综合排序");
            allSeriesFilterOption.setValue("-1");
            listD = java.util.Collections.singletonList(allSeriesFilterOption);
        }
        allSeriesFilterModel.setOptions(listD);
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel i() {
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_pay_type);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.ic_pay);
        allSeriesFilterModel.setKey("season_status");
        AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
        allSeriesFilterOption.setTitle("全部付费类型");
        allSeriesFilterOption.setValue("-1");
        allSeriesFilterModel.setOptions(
                java.util.Arrays.asList(
                        allSeriesFilterOption,
                        option("免费", "1"),
                        option("付费", "2,6"),
                        option("大会员", "4,6")));
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel j() {
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_year);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.ic_calendar);
        allSeriesFilterModel.setKey("release_date");
        AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
        allSeriesFilterOption.setTitle("全部年份");
        allSeriesFilterOption.setValue("-1");
        allSeriesFilterModel.setOptions(
                java.util.Arrays.asList(
                        allSeriesFilterOption,
                        option("2023", "[2023-01-01 00:00:00,2024-01-01 00:00:00)"),
                        option("2022", "[2022-01-01 00:00:00,2023-01-01 00:00:00)"),
                        option("2021", "[2021-01-01 00:00:00,2022-01-01 00:00:00)"),
                        option("2020", "[2020-01-01 00:00:00,2021-01-01 00:00:00)"),
                        option("2019", "[2019-01-01 00:00:00,2020-01-01 00:00:00)"),
                        option("2018", "[2018-01-01 00:00:00,2019-01-01 00:00:00)"),
                        option("2017", "[2017-01-01 00:00:00,2018-01-01 00:00:00)"),
                        option("2016", "[2016-01-01 00:00:00,2017-01-01 00:00:00)"),
                        option("2015-2010", "[2010-01-01 00:00:00,2016-01-01 00:00:00)"),
                        option("2009-2005", "[2005-01-01 00:00:00,2010-01-01 00:00:00)"),
                        option("2004-2000", "[2000-01-01 00:00:00,2005-01-01 00:00:00)"),
                        option("90年代", "[1990-01-01 00:00:00,2000-01-01 00:00:00)"),
                        option("80年代", "[1980-01-01 00:00:00,1990-01-01 00:00:00)"),
                        option("更早", "[,1980-01-01 00:00:00)")));
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel k() {
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_status);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.ic_status);
        allSeriesFilterModel.setKey("is_finish");
        AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
        allSeriesFilterOption.setTitle("全部状态");
        allSeriesFilterOption.setValue("-1");
        allSeriesFilterModel.setOptions(
                java.util.Arrays.asList(
                        allSeriesFilterOption, option("完结", "1"), option("连载", "0")));
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel l(int i4) {
        List<AllSeriesFilterOption> listD;
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_style);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.tab_dynamic);
        allSeriesFilterModel.setKey("style_id");
        if (i4 == SeriesTypeEnum.Anime.getType() || i4 == SeriesTypeEnum.ChinaAnime.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("全部风格", "-1"),
                            option("原创", "10010"),
                            option("漫画改", "10011"),
                            option("小说改", "10012"),
                            option("游戏改", "10013"),
                            option("特摄", "10102"),
                            option("布袋戏", "10015"),
                            option("热血", "10016"),
                            option("穿越", "10017"),
                            option("奇幻", "10018"),
                            option("战斗", "10020"),
                            option("搞笑", "10021"),
                            option("日常", "10022"),
                            option("科幻", "10023"),
                            option("萌系", "10024"),
                            option("治愈", "10025"),
                            option("校园", "10026"),
                            option("少儿", "10027"),
                            option("泡面", "10028"),
                            option("恋爱", "10029"),
                            option("少女", "10030"),
                            option("魔法", "10031"),
                            option("冒险", "10032"),
                            option("历史", "10033"),
                            option("架空", "10034"),
                            option("机战", "10035"),
                            option("神魔", "10036"),
                            option("声控", "10037"),
                            option("运动", "10038"),
                            option("励志", "10039"),
                            option("音乐", "10040"),
                            option("推理", "10041"),
                            option("社团", "10042"),
                            option("智斗", "10043"),
                            option("催泪", "10044"),
                            option("美食", "10045"),
                            option("偶像", "10046"),
                            option("乙女", "10047"),
                            option("职场", "10048"));
        } else if (i4 == SeriesTypeEnum.Drama.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("全部风格", "-1"),
                            option("搞笑", "10021"),
                            option("奇幻", "10018"),
                            option("战争", "10058"),
                            option("武侠", "10078"),
                            option("青春", "10079"),
                            option("短剧", "10103"),
                            option("都市", "10080"),
                            option("古装", "10081"),
                            option("谍战", "10082"),
                            option("经典", "10083"),
                            option("情感", "10084"),
                            option("悬疑", "10057"),
                            option("励志", "10039"),
                            option("神话", "10085"),
                            option("穿越", "10017"),
                            option("年代", "10086"),
                            option("农村", "10087"),
                            option("刑侦", "10088"),
                            option("剧情", "10050"),
                            option("家庭", "10061"),
                            option("历史", "10033"),
                            option("军旅", "10089"));
        } else if (i4 == SeriesTypeEnum.Movie.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("全部风格", "-1"),
                            option("短片", "10104"),
                            option("剧情", "10050"),
                            option("喜剧", "10051"),
                            option("爱情", "10052"),
                            option("动作", "10053"),
                            option("恐怖", "10054"),
                            option("科幻", "10023"),
                            option("犯罪", "10055"),
                            option("惊悚", "10056"),
                            option("悬疑", "10057"),
                            option("奇幻", "10018"),
                            option("战争", "10058"),
                            option("动画", "10059"),
                            option("传记", "10060"),
                            option("家庭", "10061"),
                            option("歌舞", "10062"),
                            option("历史", "10033"),
                            option("冒险", "10032"),
                            option("纪实", "10063"),
                            option("灾难", "10064"),
                            option("灾难", "10064"),
                            option("漫画改", "10011"),
                            option("小说改", "10012"));
        } else if (i4 == SeriesTypeEnum.Variety.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("全部风格", "-1"),
                            option("音乐", "10040"),
                            option("访谈", "10090"),
                            option("脱口秀", "10091"),
                            option("真人秀", "10092"),
                            option("选秀", "10094"),
                            option("美食", "10045"),
                            option("旅游", "10095"),
                            option("晚会", "10098"),
                            option("演唱会", "10096"),
                            option("情感", "10084"),
                            option("喜剧", "10051"),
                            option("亲子", "10097"),
                            option("文化", "10100"),
                            option("职场", "10048"),
                            option("萌宠", "10069"),
                            option("养成", "10099"));
        } else if (i4 == SeriesTypeEnum.Documentary.getType()) {
            listD =
                    java.util.Arrays.asList(
                            option("全部风格", "-1"),
                            option("历史", "10033"),
                            option("美食", "10045"),
                            option("人文", "10065"),
                            option("科技", "10066"),
                            option("探险", "10067"),
                            option("宇宙", "10068"),
                            option("萌宠", "10069"),
                            option("社会", "10070"),
                            option("动物", "10071"),
                            option("自然", "10072"),
                            option("医疗", "10073"),
                            option("军事", "10074"),
                            option("灾难", "10064"),
                            option("罪案", "10075"),
                            option("神秘", "10076"),
                            option("旅行", "10077"),
                            option("运动", "10038"),
                            option("电影", "-10"));
        } else {
            AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
            allSeriesFilterOption.setTitle("全部风格");
            allSeriesFilterOption.setValue("-1");
            listD = java.util.Collections.singletonList(allSeriesFilterOption);
        }
        allSeriesFilterModel.setOptions(listD);
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel m() {
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_type);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.tab_recommend);
        allSeriesFilterModel.setKey("season_version");
        AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
        allSeriesFilterOption.setTitle("全部类型");
        allSeriesFilterOption.setValue("-1");
        allSeriesFilterModel.setOptions(
                java.util.Arrays.asList(
                        allSeriesFilterOption,
                        option("正片", "1"),
                        option("电影", "2"),
                        option("其他", "3")));
        return allSeriesFilterModel;
    }

    public AllSeriesFilterModel n() {
        AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
        String string = this.f530b.getString(R.string.filter_year);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        allSeriesFilterModel.setTitle(string);
        allSeriesFilterModel.setIconResourceId(R.drawable.ic_calendar);
        allSeriesFilterModel.setKey("year");
        AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
        allSeriesFilterOption.setTitle("全部年份");
        allSeriesFilterOption.setValue("-1");
        allSeriesFilterModel.setOptions(
                java.util.Arrays.asList(
                        allSeriesFilterOption,
                        option("2023", "[2023,2024)"),
                        option("2022", "[2022,2023)"),
                        option("2021", "[2021,2022)"),
                        option("2020", "[2020,2021)"),
                        option("2019", "[2019,2020)"),
                        option("2018", "[2018,2019)"),
                        option("2017", "[2017,2018)"),
                        option("2016", "[2016,2017)"),
                        option("2015", "[2015,2016)"),
                        option("2014-2010", "[2010,2015)"),
                        option("2009-2005", "[2005,2010)"),
                        option("2004-2000", "[2000,2005)"),
                        option("90年代", "[1990,2000)"),
                        option("80年代", "[1980,1990)"),
                        option("更早", "[,1980)")));
        return allSeriesFilterModel;
    }
}
