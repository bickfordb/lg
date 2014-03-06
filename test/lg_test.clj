(ns lg-test
  "dummy tests to make sure the package imports ok/nothing crashes"
  (:require [clojure.test :refer :all]
            [lg :refer :all]))

(deftest a-test
         (debug "x: %s" 1)
         (info "x: %s" 2)
         (warn "x: %s" 3)
         (error "x: %s" 4)
         (trace "x: %s" 5)
         (log :error "x: %s"))
