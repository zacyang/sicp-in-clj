(ns sicp.ch3.stream-test
  (:use [midje.sweet]
        [sicp.ch3.stream]
        [clojure.test]))

(fact "stream basic data structure test"
      (let [test-stream (cons-stream 1  2)]
      (-> test-stream stream-car) => 1
      (-> test-stream stream-cdr) => 2
      (-> test-stream stream-null?) => false
))


(fact "stream filter, quick test"
         (let [test-stream (stream-enumerate-interval 0 100000000 )]
           (stream-car  (stream-filter #(> %1 3) test-stream)) => 4
           (stream-car  (stream-cdr  (stream-filter #(> %1 3) test-stream))) => 5

           (->   (stream-filter #(> %1 3) test-stream)
                 stream-cdr
                 stream-cdr
                 stream-cdr
                 stream-car)
           => 7))

(fact "primes as stream"
      (stream-ref primes 50) => 233)

(fact "stream maps basic tests"
      (let [s1 (stream-enumerate-interval 1 1)
            s2 (stream-enumerate-interval 2 2)]
        (stream-car (stream-maps + s1 s2)) => 3)

      (let [s1 (stream-enumerate-interval 1000 100000 )
            s2 (stream-enumerate-interval 1 100000)]
        (stream-car (stream-maps + s1 s2)) => 1001)

      (let [s1 (stream-enumerate-interval 1000 100000 )
            s2 (stream-enumerate-interval 2 100000)]
        (stream-ref (stream-maps + s1 s2) 10) => 1022)

      (let [s1 (stream-enumerate-interval 1000 100000 )
            s2 (stream-enumerate-interval 2 100000)]
        (stream-ref (add-stream s1 s2) 10) => 1022)
)

(fact "fib in stream manner"
      (stream-ref fibs 1) => 1)

(fact "stream map, should also returns a lazy seq"
      (let [test-stream (stream-enumerate-interval 0 100000000 )
            *counter* (atom 0)
            _ (stream-map (fn [_] (swap! *counter* inc)) test-stream)]

        @*counter* => 1))

(fact "3.54 def mul-streams and factorials"
      (let [ test-stream (mul-stream integers integers)]
        (stream-car test-stream) => 1
        (stream-ref test-stream 2 ) => 9
        (stream-ref test-stream 3 ) => 16
        (stream-ref test-stream 10 ) => 121)
      
      (stream-car factorials) => 1
      (stream-ref factorials 0) => 1
      (stream-ref factorials 1) => 2
      (stream-ref factorials 2) => 6
      (stream-ref factorials 3) => 24
      (stream-ref factorials 4) => 120
)
