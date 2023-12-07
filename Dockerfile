FROM public.ecr.aws/amazoncorretto/amazoncorretto:17.0.7-al2023-headful AS builder
COPY . ./
RUN yum -y install findutils
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM public.ecr.aws/amazoncorretto/amazoncorretto:17.0.7-al2023-headful
COPY --from=builder ./build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=dev"]




