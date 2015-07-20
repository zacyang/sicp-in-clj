(ns sicp.ch3.stream-test
  (:use [midje.sweet]
        [sicp.ch3.stream]
        [clojure.test]))

(fact "stream basic data structure test"
      (let [test-stream (cons-stream 1 (fn [] 2))]
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


;; (fact "stream map, should also returns a lazy seq"
;;       (let [test-stream (stream-enumerate-interval 0 100000000 )
;;             *counter* (atom 0)
;;             ;; x (stream-map (fn [_] (swap! *counter* inc)) (test-stream))
            
;;             ]
;;         (let  [_ (stream-map (fn [_] (swap! *counter* inc)) (test-stream))]
;;           @*counter* => 1
;;           )
        
;;         ))



      
