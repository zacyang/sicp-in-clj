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

(deftest sicp-chapter2-2-18
  (testing "should return a the same list when list contains only one item"
    (is (= '(1) (reverse-list '(1))))
    )
  (testing "should return empty list  list when input list is empty"
    (is (= '() (reverse-list '())))
    )
  (testing "should return reversed list list when input list contains multi-item"
    (is (= '(3 2 1) (reverse-list '(1 2 3))))
    )
  
  )
