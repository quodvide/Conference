# 카카오페이 서버개발자 사전과제(회의실) 


> 서버개발자 지원자
> **송민석** 

## 문제해결 전략 
우선 프로젝트에 대한 간단히 설명하자면 Java Spring + JPA으로 백엔드 구현 하였고, JavaScript + BootStrap + GoogleFont로 프론트엔드 구현하였습니다.  
도메인에는 회의실을 표현하기 위한 ConferenceRoom, 예약을 표현하기 위한 Reservation을 만들어 놓았고, 예약하기를 위한 ReservationDto를 만들어 사용하였습니다.  
또한 예약하기, 예약현황 화면을 위한 간단한 HomeController를 만들어 놓았고, 예약하기, 예약현황의 Api를 처리해주기 위한 RestController인 ApiReservationController를 만들었습니다.  

프로젝트를 시작하기 전 생각해 볼만한 문제가 크게 세 가지 있었습니다.  
우선 처음으로 예약시간이 00:25 등으로 30분으로 나누어지지 않거나 예약횟수가 0으로 입력 되는 등의 데이터 확인은 어떻게 처리할것인지를 생각해 보았는데 ReservationService단에서 확인하여 처리해주면 될 것이라고 생각하였고, ReservationService와 ReservationDto에 올바른 값인지 확인하는 메소드들을 넣어서 해결하였습니다.  
그리고 예약시간이 다른 예약들과 겹치는 것을 어떻게 확인할 것인지도 생각해보았는데 Reservation 도메인에 LocalTime으로 값들을 저장하여 LocalTime의 메소드인 isAfter, isBefore등을 활용하여 겹치는지 여부를 확인할 수 있다고 생각하였고, 예약일자와 겹치는 모든 예약들에 대하여 겹치는지 여부를 확인하게 한 후 예약을 하도록 구현하였습니다.  
마지막으로 반복횟수로 인해 여러개의 예약정보를 만들어서 저장해야하는 경우를 어떻게 처리할 것인지 였는데 ReservationDto에서 Reservation으로 전환하는 메소드를 만들어 List<Reservation>을 반환하게 하여 예약할 수 있게 만들어서 해결하였습니다. 

## 개발환경 
Mac OS X 10.10  
Intellij 2018.2.3 Community Edition  

## 프로젝트 빌드와 실행 방법
프로젝트 안의 KakaoApplication을 실행하거나 
프로젝트 루트 안에서 ./gradlew build로 빌드 후 java -jar ./build/libs/Songminseok-1.0-SNAPSHOT.jar 명령으로 실행하여 웹브라우저 localhost에서 서비스 사용 할 수 있습니다. 




