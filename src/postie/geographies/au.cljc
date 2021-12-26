(ns postie.geographies.au
  (:require [malli.generator :as g]
            [postie.geographies.common :refer [preprocess default-formatter]]))

(def pattern #"^[0-9]{4}$")

(defn valid?
  [postcode]
  (->> postcode
       preprocess
       (re-matches pattern)
       some?))

(def format-postcode (partial default-formatter valid?))

(def generator
  (g/generator [:re pattern]))

(def strategy
  {:generator generator
   :formatter format-postcode
   :valid? valid?})
