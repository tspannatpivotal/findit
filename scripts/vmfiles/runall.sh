clear

export XD_HOME=/opt/pivotal/spring-xd/xd

nohup /opt/pivotal/spring-xd/hsqldb/bin/hsqldb-server &
nohup /home/gpadmin/Downloads/spring-xd-1.0.0.M7/redis/bin/redis-server &
sudo /etc/init.d/rabbitmq-server start

nohup /opt/pivotal/spring-xd/gemfire/bin/gemfire-server /opt/pivotal/spring-xd/gemfire/config/cq-demo.xml &

sleep 2

sudo /etc/init.d/spring-xd-admin start
sudo /etc/init.d/spring-xd-container start

