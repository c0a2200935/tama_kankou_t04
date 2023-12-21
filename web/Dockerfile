FROM httpd
RUN apt-get update
RUN apt-get upgrade -y

COPY conf/httpd.conf /usr/local/apache2/conf/httpd.conf
ADD conf/httpd-proxy.conf /usr/local/apache2/conf/extra/


# FROM ubuntu

# RUN apt-get update
# RUN apt-get upgrade -y

# RUN apt-get install -y git \
#     wget \
#     curl \
#     nodejs
# RUN apt-get install -y npm
# RUN npm install -g n create-vite
# RUN n 18.17.1

# RUN mkdir /project
# # RUN cd home/02_advanced/ && npm install --save-dev vite

# EXPOSE 5173


