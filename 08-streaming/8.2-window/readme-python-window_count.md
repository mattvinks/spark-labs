<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md)

Lab 8.2 - Spark Streaming Windowed Calculations
=================

### Overview
Submit a job for Spark Streaming and doing 'windowed count'

### Depends On 
None

### Run time
30-40 mins

## STEP 1: Run Netcat Server

Use `nc` command to move text you type to port 9999
Open an terminal and run this command at prompt

```bash
    $ nc -lk 9999

    # if this gives an error like 'Protocol not available' use this
    # $  ~/bin/nc  -lk 9999
```

## STEP 2: Run the python file

Go to the project root directory
```bash
    $	cd ~/spark-labs/08-streaming/8.2-window
```
Run the python file **python/windowed_count.py** using spark submit command

```bash
	$	~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  python/windowed_count.py
```

## STEP 3: Feed some data into netcat window
**=> Try typing / pasting the following text in netcat**  
```
1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
1420070404320,ip_9,user_5,clicked,foxnews.com,campaign_2,173,session_24
```

**=> Notice the kafka streaming console**  
```console
-------------------------------------------
Time: 2019-07-22 13:18:30
-------------------------------------------
(u'clicked', 1)
(u'viewed', 1)
(u'clicked', 1)
(u'blocked', 1)
(u'viewed', 1)
(u'blocked', 1)
```

## STEP 4: Enable Window Count

* Edit the file python/windowed_count.py
* Run using the spark-submit command

** ==> fix TODO-1 to enable Window count**
** ==> save and run the file**
```bash
    $    ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  python/windowed_count.py
```

**=> Paste some logs in netcat window**

**=> Hit Ctrl+C in netcat terminal to terminate Spark streaming**

**=> Inspect some files, what do you see?**

Test with following lines
```
1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
1420070404320,ip_9,user_5,clicked,foxnews.com,campaign_2,173,session_24
```
