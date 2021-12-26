(ns postie.core
  (:require [postie.geographies.au :as au]
            [postie.geographies.ca :as ca]
            [postie.geographies.ch :as ch]))

(defn valid-de-postcode? [])
(defn format-de-postcode [])

(defn valid-gb-postcode? [])
(defn format-gb-postcode [])

(defn valid-nl-postcode? [])
(defn format-nl-postcode [])

(defn valid-no-postcode? [])
(defn format-no-postcode [])

(defn valid-pl-postcode? [])
(defn format-pl-postcode [])

(defn valid-ro-postcode? [])
(defn format-ro-postcode [])

(defn valid-se-postcode? [])
(defn format-se-postcode [])

(defn valid-us-postcode? [])
(defn format-us-postcode [])

;; https://en.wikipedia.org/wiki/List_of_postal_codes
(def country-codes-to-behaviour
  {:ad {}
   :af {}
   :ai {}
   :al {}
   :am {}
   :aq {}
   :ar {}
   :ax {}
   :az {}
   :bb {}
   :bd {}
   :bg {}
   :bh {}
   :bl {}
   :bm {}
   :bn {}
   :br {}
   :bt {}
   :by {}
   :cc {}
   :cl {}
   :cn {}
   :co {}
   :cr {}
   :cu {}
   :cv {}
   :cx {}
   :cy {}
   :cz {}
   :de {:validator valid-de-postcode?
        :formatter format-de-postcode
        :generator nil}
   :do {}
   :dz {}
   :ec {}
   :ee {}
   :eg {}
   :es {}
   :et {}
   :fi {}
   :fk {}
   :fm {}
   :fo {}
   :fr {:validator valid-de-postcode?
        :formatter format-de-postcode
        :generator nil}
   :gb {:validator valid-gb-postcode?
        :formatter format-gb-postcode
        :generator nil}
   :ge {}
   :gf {}
   :gg {}
   :gh {}
   :gi {}
   :gl {}
   :gn {}
   :gp {}
   :gr {}
   :gs {}
   :gt {}
   :gu {}
   :gw {}
   :hn {}
   :hr {}
   :ht {}
   :hu {}
   :id {}
   :ie {}
   :il {}
   :im {}
   :in {}
   :io {}
   :iq {}
   :ir {}
   :is {}
   :it {}
   :je {}
   :jm {}
   :jo {}
   :jp {}
   :ke {}
   :kg {}
   :kh {}
   :kr {}
   :kw {}
   :ky {}
   :kz {}
   :la {}
   :lb {}
   :lc {}
   :li {}
   :lk {}
   :lr {}
   :ls {}
   :lt {}
   :lv {}
   :ma {}
   :mc {}
   :md {}
   :me {}
   :mf {}
   :mg {}
   :mh {}
   :mk {}
   :mm {}
   :mn {}
   :mp {}
   :mq {}
   :ms {}
   :mt {}
   :mu {}
   :mv {}
   :mx {}
   :my {}
   :mz {}
   :nc {}
   :ne {}
   :nf {}
   :ng {}
   :ni {}
   :nl {:validator valid-nl-postcode?
        :formatter format-nl-postcode
        :generator nil}
   :no {:validator valid-no-postcode?
        :formatter format-no-postcode
        :generator nil}
   :np {}
   :om {}
   :pa {}
   :pe {}
   :pf {}
   :pg {}
   :ph {}
   :pk {}
   :pl {:validator valid-pl-postcode?
        :formatter format-pl-postcode
        :generator nil}
   :pm {}
   :pn {}
   :pr {}
   :ps {}
   :pt {}
   :pw {}
   :py {}
   :re {}
   :ro {:validator valid-ro-postcode?
        :formatter format-ro-postcode
        :generator nil}
   :rs {}
   :ru {}
   :sa {}
   :sd {}
   :se {:validator valid-se-postcode?
        :formatter format-se-postcode
        :generator nil}
   :sg {}
   :sh {}
   :si {}
   :sj {}
   :sk {}
   :sm {}
   :sn {}
   :so {}
   :sv {}
   :sz {}
   :tc {}
   :tf {}
   :th {}
   :tj {}
   :tm {}
   :tn {}
   :tr {}
   :tt {}
   :tw {}
   :tz {}
   :ua {}
   :um {}
   :us {:validator valid-us-postcode?
        :formatter format-us-postcode
        :generator nil}
   :uy {}
   :uz {}
   :va {}
   :vc {}
   :ve {}
   :vg {}
   :vi {}
   :vn {}
   :wf {}
   :ws {}
   :xk {}
   :yt {}
   :zm {}})

(defn fetch-strategy [country-code]
  (condp some [country-code]
    #{:au :nz  :za} au/strategy
    #{:ca} ca/strategy
    #{:ch :at :be :dk :lu} ch/strategy))

(defn has-postcodes? [country-code]
  (not (false? (fetch-strategy country-code))))

(defn validate [country-code postcode]
  (when-let [{validator :validator} (fetch-strategy country-code)]
    (validator postcode)))

(defn format-postcode [country-code postcode]
  (if-let [{formatter :formatter} (fetch-strategy country-code)]
    (formatter postcode)
    true))

(defn valid?
  [country-code postcode]
  (if-let [{validator :validator} (fetch-strategy country-code)]
    (validator postcode)
    true))
