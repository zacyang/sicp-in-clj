(ns sicp.ch3.stream)


(fact "stream basic data structure test"
      (let [test-stream (cons-stream (1 #(2)))]
        (stream-car test-stream) => 1
        (stream-cdr test-stream) => 2
        (stream-null? test-stream) => false))
