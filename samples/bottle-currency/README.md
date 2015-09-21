# Currency Converter

## Purpose

Currency Converter is a web app for converting currencies. 

## Technology Stack

 * ActivePython - http://www.activestate.com/activepython
 * Bottle web framework - http://bottlepy.org/
 * Redis NoSQL database - http://redis.io/

The app uses Foxrate XML-RPC API to obtain actual currency
exchange rates. The data is retained in the local Redis 
database for 3 hours.

## Usage

### Deployment

    $ cf push -n

### Connecting to the Redis server

	$ cf create-service redis redis --plan free

Modify manifest.yml as below (by adding the last line).

applications:
- name: bottle-currency
  mem: 128M
  services: [redis]
  
  


