# run-together
혼자 하는 러닝의 지루함을 덜고, 가까운 지역에서 비슷한 목표를 가진 러너들과 쉽게 연결되어 함께 운동할 수 있는 플랫폼입니다.

### 프로젝트에서 코드 스타일 체크 사용 방법
1. Checkstyle
    - checkstyle 8.24 버전 이상을 사용해야 한다.
   ```shell
   java -DsuppressionFile=./codestyle/naver-checkstyle-suppressions.xml -jar checkstyle-10.18.1.jar -c ./codestyle/naver-checkstyle-rules.xml src
   ```

2. IntelliJ
   - File > Setting 메뉴로 이동
   - Editor > Code Style > Java 항복으로 이동
   - Schema 항복의 오른쪽 톱니바퀴 아이콘 클릭
   - Import Schema > IntelliJ IDEA Code Style XML 선택