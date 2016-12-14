############################ NGINX WEB SERVER ################################
# The standard nginx container just runs nginx. The configuration file added
# below will be used by nginx.
#FROM nginx

# Copy the nginx configuration file. This sets up the behavior of nginx, most
# importantly, it ensure nginx listens on port 8080. Google App Engine expects
# the runtime to respond to HTTP requests at port 8080.
#COPY nginx.conf /etc/nginx/nginx.conf

# create log dir configured in nginx.conf
#RUN mkdir -p /var/log/app_engine

# Create a simple file to handle heath checks. Health checking can be disabled
# in app.yaml, but is highly recommended. Google App Engine will send an HTTP
# request to /_ah/health and any 2xx or 404 response is considered healthy.
# Because 404 responses are considered healthy, this could actually be left
# out as nginx will return 404 if the file isn't found. However, it is better
# to be explicit.
#RUN mkdir -p /usr/share/nginx/www/_ah && \
#    echo "healthy" > /usr/share/nginx/www/_ah/health

# Finally, all static assets.
#ADD www/ /usr/share/nginx/www/
#RUN chmod -R a+r /usr/share/nginx/www


################## JAVA + JETTY ###################

FROM gcr.io/google_appengine/jetty9


################## MAVEN ##########################
# update dpkg repositories
#RUN apt-get update

# install wget
#RUN apt-get install -y wget

# get maven 3.3.9
#RUN wget --no-verbose -O /tmp/apache-maven-3.3.9.tar.gz http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz

# verify checksum
#RUN echo "87e5cc81bc4ab9b83986b3e77e6b3095 /tmp/apache-maven-3.3.9.tar.gz" | md5sum -c

# install maven
#RUN tar xzf /tmp/apache-maven-3.3.9.tar.gz -C /opt/
#RUN ln -s /opt/apache-maven-3.3.9 /opt/maven
#RUN ln -s /opt/maven/bin/mvn /usr/local/bin
#RUN rm -f /tmp/apache-maven-3.3.9.tar.gz
#ENV MAVEN_HOME /opt/maven

#RUN which java

################## JAVA + JETTY ###################
#RUN mvn clean install
ADD target/GCPJavaSample.war $JETTY_BASE/webapps/root.war
ADD model/M5P_20161012.model /tmp/M5P_20161012.model
WORKDIR $JETTY_BASE
RUN java -jar $JETTY_HOME/start.jar --approve-all-licenses --add-to-startd=jmx,stats,hawtio \
 && chown -R jetty:jetty $JETTY_BASE