(ns sicp-in-clj.core-test
  (:require [clojure.test :refer :all]
            [sicp-in-clj.core :refer :all]))

(deftest sicp-chapter2-2-17
  (testing "should return the last item in the list"
    (is (= 1 ( last-pair '( 1))))
    )
  (testing "should return the nil if list is empty"
    (is (= nil ( last-pair '())))
    )
  (testing "should return the last item  if list contains multi-item")
  (is (= 73 ( last-pair '(1 2 3 4 5 73))))
    
  
  
    
)
