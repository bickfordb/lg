# lg

Clojure utility interface to slf4j

## Usage

Add `[lg "1.0.0"]` to the dependencies section of your "project.clj"

```clojure

(ns my.ns
  (:use lg))

(defn hairiness
  [argument]
  (info "hairiness called with %s" argument))

```


## License

Copyright © 2014 Brandon Bickford

Distributed under the GNU GPL V3 or later. Refer to www.gnu.org for a copy.
