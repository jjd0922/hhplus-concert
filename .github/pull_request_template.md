## PR 설명
시나리오 선정 : 콘서트 예약 대기열 서비스

### [STEP-7]
마일스톤 : https://github.com/users/jjd0922/projects/1/views/2?layout=roadmap
요구사항 분석 : https://github.com/jjd0922/hhplus-concert/wiki/%EC%BD%98%EC%84%9C%ED%8A%B8-%EC%98%88%EC%95%BD-%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EB%B6%84%EC%84%9D
시퀀스 다이어그램 : https://github.com/jjd0922/hhplus-concert/wiki/%EC%8B%9C%ED%80%80%EC%8A%A4-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8
플로우 차트 : https://github.com/jjd0922/hhplus-concert/wiki/%ED%94%8C%EB%A1%9C%EC%9A%B0-%EC%B0%A8%ED%8A%B8

### [STEP-8]
ERD 설계 : https://github.com/jjd0922/hhplus-concert/wiki/ERD-%EC%84%A4%EA%B3%84
API 명세 : cc8d906
mock api 구현 : 5e5893e

## 리뷰 포인트

* 토큰 검증의 경우 대부분의 API 로직에 포함되어 있으므로 Intercepter 를 활용하여 동일하게 로직이 동작하도록 만들면 어떻지 궁금합니다.
* 이번 API 명세서 작업을 Intellij .http를 활용하여 작업하였으나 다른 명세서 작성 툴이나 방법이 있는지 궁금합니다. 


## 이번주 KPT 회고
## Keep
여러 툴을 사용하여 요구분석 및 설계 작업을 하였고 gitHub을 활용하여 문서정리을 진행함

## Problem
시퀀스 다이어그램 작성 시 상당 시간 소요

## Try
UML 작성이나 API 명세서 작성에 있어서 좀 더 다양한 툴 사용해보기