(ns postie.core
  (:require [clojure.string :as string]
            [malli.generator :as g]
            [postie.geographies.au :as au]
            [postie.geographies.ca :as ca]))

(defn valid-ch-postcode? [postcode]
  [true [postcode]])

(defn format-ch-postcode [postcode]
  postcode)

(defn valid-au-postcode? [])
(defn format-au-postcode [])

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
   :at {:validator valid-ch-postcode?
        :formatter format-ch-postcode
        :generator nil}
   :au {:validator au/valid?
        :formatter au/format-postcode
        :generator au/generator}
   :ax {}
   :az {}
   :bb {}
   :bd {}
   :be {:validator valid-ch-postcode?
        :formatter format-ch-postcode
        :generator nil}
   :bg {}
   :bh {}
   :bl {}
   :bm {}
   :bn {}
   :br {}
   :bt {}
   :by {}
   :ca {:validator ca/valid?
        :formatter ca/format-postcode
        :generator ca/generator}
   :cc {}
   :ch {:validator valid-ch-postcode?
        :formatter format-ch-postcode
        :generator nil}
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
   :dk {:validator valid-ch-postcode?
        :formatter format-ch-postcode
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
   :lu {:validator valid-ch-postcode?
        :formatter format-ch-postcode
        :generator nil}
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
   :nz {:validator valid-au-postcode?
        :formatter format-au-postcode
        :generator nil}
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
   :za {:validator valid-au-postcode?
        :formatter format-au-postcode
        :generator nil}
   :zm {}})

(defn validate [country-code postcode]
  (when-let [{validator :validator} (get country-codes-to-behaviour country-code)]
    (validator postcode)))

(defn format-postcode [country-code postcode]
  (if-let [{formatter :formatter} (get country-codes-to-behaviour country-code)]
    (formatter postcode)
    true))

(defn valid?
  [country-code postcode]
  (if-let [{validator :validator} (get country-codes-to-behaviour country-code)]
    (validator postcode)
    true))
