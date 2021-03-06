(ns sicp.ch2.core-test
  (:require [clojure.test :refer :all]
            [sicp.ch2.core :refer :all]))

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

(deftest sicp-chapter2-2-23
  (testing "unsorted collection"
    (is (= true (element-of-set? 3 '(1 2 3)))))
    (is (= false (element-of-set? 1 '(2))))
    (is (= false (element-of-set? 1 '())))
    (is (= false (element-of-set? nil '(2))))

  (testing "unsorted collection conj"
    (is (= '(1) (adjoin-set 1 '())))
    (is (= '(2 3) (adjoin-set 2 '(3))))
                
    (testing "interaction set"
     (is (= '(1) (interaction-set '(1 2) '(1 3))))
     (is (= '() (interaction-set '(5 2) '(1 3))))
     (is (= '(1 2) (interaction-set '( 5 6 3 1 3 2) '(1 2))))
      )
    ))

(deftest sicp-chapter2-2-29
  (testing "impl union-set for unsorted col"
    (is (= '(1 2) (union-set '(1) '(2))))
    (is (= '(1 2) (union-set '(1 2) '(1 2))))
    (is (= '() (union-set '() '())))
    (is (= '(1) (union-set '() '(1))))
    (is (= '(1) (union-set '(1) '())))
    )
  )
;; (deftest sicp-chapter2-2-30
;;   "tree impl"
;;   (testing "get the entry of a tree"
;;     (is (= 1 (entry '(1 2 3))))
;;     )
;;   )


;;; lay quick sort testing
(deftest lazy-sort-testing
  (testing ""
    (is (=  (quick-lazy-sort '(3 2 1))  [1 2 3]))
    (is (=  (quick-lazy-sort [3 2 1 5 4]) [1 2 3 4 5]))
    (is (= (quick-lazy-sort (range 100)) (range 100)))
    ))
