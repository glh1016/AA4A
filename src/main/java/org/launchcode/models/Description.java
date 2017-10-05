package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by t420-11 on 10/5/2017.
 */

@Entity
public class Description {
        @Id
        @GeneratedValue
        private int id;

        @NotNull
        @Size(min=3, max=15)
        private String name;
}
