# incheon-airport-delay
인천공항 연착정보를 조회할 수 있는 웹 사이트 프로젝트
데이터는 항공정보포탈시스템(http://airportal.go.kr/life/airinfo/RbHanFrmMain.jsp)에서 2018.01 ~ 2019.06 까지의 데이터를 크롤링하여 수집함

<hr/>

인천공항에서 출발하는 비행기들을 지역, 항공사 조회를 통해 아래의 정보들을 확인할 수 있음

* 10분 이내 지연율
* 30분 초과 지연율
* 평균 지연 분
* 총 운항 횟수
* 총 회항 횟수
* 총 결항 횟수

<hr/>

## 개발환경
* Springboot 2.1.7.RELEASE
* JPA
* Thymeleaf
* tomcat
* mysql

<hr/>

## 구현된 웹사이트 (언제 없어질지 모름)
* http://13.124.152.200/v1/main
