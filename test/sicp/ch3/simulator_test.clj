(ns sicp.ch3.simulator
  (:use [midje.sweet]
        [sicp.ch3.simulator]))

(defn- dummy-fn-just-return 
  [] 
  (fn [] 
    (swap! counter #(inc %))
          @counter
          ))

(fact "should be able to make a wire"
      (let [fn-under-test (make-wire)]
        ((fn-under-test :get-signal)) => 0
        ((fn-under-test :set-signal!) 1) => 1
        ;;then the signal should be stored as 1
        ((fn-under-test :get-signal)) => 1
        ((fn-under-test :add-action!) (fn [] 2)) => 1
          ))

(fact "call-each test"
      (def  counter (atom 0))

      
      (let [fn-under-test call-each
            procs  (take 3 (repeat (dummy-fn-just-return)))]
        (fn-under-test procs) => :done
        @counter => 3
        ))

