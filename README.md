# Feature Toggles

### Run backend 
1. Start Redis cache 
```bash
docker run -p 6379:6379 -d redis:5.0.6
```
2. Build the app 
```
./gradlew clean build
```
3. Run the app - `com.igor.feature.FeatureApplication` from Intellij IDEA
or run 
```
./gradlew bootRun
```

### Feature flags
Based on [FF4J](https://github.com/ff4j/ff4j)
DB table - `ff4j_features`
Feature RBAC table - `ff4j_roles`
NOTE: role name is a privilige, not role
For more details about roles and priviliges please take a look at section `Permissions` below

How to check if a feature flag is enabled/disabled: </br>
`
curl --request GET 'http://localhost:8080/api/ff4j/check/fflag-qc'
`
How to enable a feature flag: </br>
`
curl --request POST 'http://localhost:8080/api/ff4j/store/features/fflag-qc/enable' --header 'Content-Type: application/json
`
How to disable a feature flag: </br>
`
curl --request POST 'http://localhost:8080/api/ff4j/store/features/fflag-qc/disable' --header 'Content-Type: application/json
`
