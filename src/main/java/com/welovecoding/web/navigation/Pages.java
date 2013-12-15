package com.welovecoding.web.navigation;

/**
 *
 * @author Benny
 */
public enum Pages {
  ADMIN_INDEX {
            @Override
            public String toString() {
              return "/admin/index";
            }
          },
  LOGIN {
            @Override
            public String toString() {
              return "/login?faces-redirect=true";
            }
          }
}
