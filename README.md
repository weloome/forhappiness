# README #

### 토이프로젝트 주제
* 미아가 된 반려동물 찾기 서비스

 * mysql
```shell
docker run --name mysql -p 3306:3306 -e MYSQL_DATABASE=wimp -e MYSQL_ROOT_PASSWORD=secret -d mysql:8.0.31
```
 * redis
```shell
docker run -d -p 6379:6379 --name redis redis:latest
```
 * ERD
   * link: https://dbdiagram.io/d/64d1ad2e02bd1c4a5e65c9db