# Tour App Deployment Guide

## Prerequisites
- Java 17+ installed
- Maven 3.9+
- MySQL database (local, RDS, or cloud)
- Git account
- Cloud platform account (Heroku, Railway, AWS, etc.)

---

## Step 1: Build the Application

```bash
mvn clean package
```

This creates `target/tour-website-1.0.0.jar` (production-ready JAR).

---

## Step 2: Test Locally with Production Config

### Option A: Use H2 (In-Memory Database)
```bash
java -jar target/tour-website-1.0.0.jar
```

### Option B: Use MySQL
First, update `src/main/resources/application-prod.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tourapp
spring.datasource.username=root
spring.datasource.password=yourpassword
```

Then run:
```bash
java -Dspring.profiles.active=prod -jar target/tour-website-1.0.0.jar
```

### Option C: Docker Compose (Recommended)
```bash
docker-compose up
```
Access: http://localhost:8080

---

## Step 3: Deploy to Cloud

### **Option A: Heroku (Easiest)**

1. Install Heroku CLI: https://devcenter.heroku.com/articles/heroku-cli
2. Login: `heroku login`
3. Initialize git (if not already):
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   ```
4. Create Heroku app:
   ```bash
   heroku create your-tour-app-name
   ```
5. Add MySQL database (JawsDB is free tier):
   ```bash
   heroku addons:create jawsdb:kitefin
   ```
6. Deploy:
   ```bash
   git push heroku main
   ```
7. View logs:
   ```bash
   heroku logs --tail
   ```

---

### **Option B: Railway.app (Simplest)**

1. Go to https://railway.app
2. Click "New Project" → Select "Deploy from GitHub"
3. Connect your GitHub repo containing this project
4. Railways auto-detects Maven build
5. Add MySQL service:
   - Click "Add"
   - Select "MySQL"
   - Connect to same project
6. Set environment variables:
   - `SPRING_PROFILES_ACTIVE=prod`
7. Auto-deploys on push to GitHub!

---

### **Option C: AWS Elastic Beanstalk**

1. Install AWS CLI & EB CLI
2. Configure credentials: `aws configure`
3. In project directory:
   ```bash
   eb init -p java-17-corretto -r us-east-1
   eb create tour-app-env
   eb deploy
   ```
4. Create RDS MySQL database via AWS Console
5. Update environment variables in EB dashboard

---

### **Option D: Docker Hub + Any Cloud**

1. Build Docker image:
   ```bash
   docker build -t your-username/tour-app:1.0.0 .
   ```
2. Push to Docker Hub:
   ```bash
   docker login
   docker push your-username/tour-app:1.0.0
   ```
3. Deploy to AWS ECS, Google Cloud Run, Azure Container Instances, etc.

---

## Step 4: Configure Production Database

Update `src/main/resources/application-prod.properties`:

**For AWS RDS MySQL:**
```properties
spring.datasource.url=jdbc:mysql://your-rds-endpoint.amazonaws.com:3306/tourapp
spring.datasource.username=admin
spring.datasource.password=your-secure-password
```

**For JawsDB (Heroku):**
- Heroku auto-sets `JAWSDB_URL` environment variable
- Spring auto-detects it; no manual config needed

**For PlanetScale:**
```properties
spring.datasource.url=jdbc:mysql://your-planetscale-host/tourapp
spring.datasource.username=root
spring.datasource.password=your-password
```

---

## Step 5: Set Custom Domain

1. **Buy domain** from Namecheap, GoDaddy, or Route 53
2. **Configure DNS:**
   - Heroku: Settings → Domains → Add custom domain
   - Railway: Domains tab → Connect domain
   - AWS: Route 53 → Create record set pointing to load balancer

3. **Example for Heroku:**
   ```bash
   heroku domains:add www.yourtourapp.com
   ```

---

## Step 6: Monitor & Maintain

### Check Logs
```bash
heroku logs --tail          # Heroku
railway environment logs    # Railway
eb logs                      # AWS
docker logs container_id    # Docker
```

### Update Application
```bash
git push heroku main        # Auto-deploys
# or manually:
mvn clean package
eb deploy
```

### Backup Database
- AWS RDS: Automated snapshots
- JawsDB: Use `mysqldump`
- PlanetScale: Built-in backups

---

## Security Checklist

- [ ] Change default database passwords
- [ ] Enable HTTPS/SSL (cloud platforms auto-enable)
- [ ] Update security settings in `SecurityConfig.java`
- [ ] Store sensitive data in environment variables, not in code
- [ ] Enable database backups
- [ ] Set up monitoring/alerts
- [ ] Review Spring Security configuration

---

## Troubleshooting

**App won't start:**
```bash
# Check logs
heroku logs --tail
# Ensure Java version is 17+
# Verify database credentials
```

**Database connection error:**
- Verify database host, username, password
- Check firewall rules (if self-hosted)
- Ensure database is running

**Port already in use:**
- Spring will use PORT environment variable on cloud platforms
- Locally, use: `java -Dserver.port=9090 -jar app.jar`

---

## Cost Estimates (as of 2024)

| Platform | Cost |
|----------|------|
| Heroku | $7-50/month |
| Railway | $5-20/month |
| AWS (free tier eligible) | Free-$50/month |
| Linode | $5-10/month |
| DigitalOcean | $5-12/month |

---

## Support

Need help? Check logs first:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

Visit: https://spring.io/guides/gs/deploying-spring-boot-apps/
