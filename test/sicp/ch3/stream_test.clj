(ns sicp.ch3.stream-test
  (:use [midje.sweet]
        [sicp.ch3.stream]))

(fact "stream basic data structure test"
      (let [test-stream (cons-stream 1 (fn [] 2))]
      (-> test-stream stream-car) => 1
      (-> test-stream stream-cdr) => 2
      (-> test-stream stream-null?) => false
))



(fact "stream filter, quick test"
      (let [test-stream (stream-enumerate-interval 0 1 )]
        (first  (stream-filter zero? test-stream)) => 0
))




      
