package com.xiaomi.security.xsof.base;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ScoreConfigBean {
    private List<EventListBean> eventList;
    private List<EventTimeListBean> eventTimeList;
    private List<TimeWindowsBean> timeWindows;

    public static class EventListBean {
        private String eventName;
        private int eventScore;

        public String getEventName() {
            return this.eventName;
        }

        public int getEventScore() {
            return this.eventScore;
        }

        public void setEventName(String str) {
            this.eventName = str;
        }

        public void setEventScore(int i10) {
            this.eventScore = i10;
        }
    }

    public static class EventTimeListBean {
        private String eventName;
        private List<EventTimeScoreBean> eventTimeScore;

        public static class EventTimeScoreBean {
            private int score;
            private int time;

            public int getScore() {
                return this.score;
            }

            public int getTime() {
                return this.time;
            }

            public void setScore(int i10) {
                this.score = i10;
            }

            public void setTime(int i10) {
                this.time = i10;
            }
        }

        public String getEventName() {
            return this.eventName;
        }

        public List<EventTimeScoreBean> getEventTimeScore() {
            return this.eventTimeScore;
        }

        public void setEventName(String str) {
            this.eventName = str;
        }

        public void setEventTimeScore(List<EventTimeScoreBean> list) {
            this.eventTimeScore = list;
        }
    }

    public static class TimeWindowsBean {
        private int timeWindow;
        private double wight;

        public int getTimeWindow() {
            return this.timeWindow;
        }

        public double getWight() {
            return this.wight;
        }

        public void setTimeWindow(int i10) {
            this.timeWindow = i10;
        }

        public void setWight(double d10) {
            this.wight = d10;
        }
    }

    public List<EventListBean> getEventList() {
        return this.eventList;
    }

    public List<EventTimeListBean> getEventTimeList() {
        return this.eventTimeList;
    }

    public List<TimeWindowsBean> getTimeWindows() {
        return this.timeWindows;
    }

    public void setEventList(List<EventListBean> list) {
        this.eventList = list;
    }

    public void setEventTimeList(List<EventTimeListBean> list) {
        this.eventTimeList = list;
    }

    public void setTimeWindows(List<TimeWindowsBean> list) {
        this.timeWindows = list;
    }
}
