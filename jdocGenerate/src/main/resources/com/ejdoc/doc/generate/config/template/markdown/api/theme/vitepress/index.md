---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
  name: "${prop.name!}"
  text: "API文档"
  tagline: ${prop.version!}
  actions:
    - theme: brand
      text: 首页
      link: /markdown-examples
    - theme: alt
      text: API Examples
      link: /api-examples
    - theme: alt
      text: API列表
      link: /api-tables
features:
  - title: Feature A
    details: Lorem ipsum dolor sit amet, consectetur adipiscing elit
  - title: Feature B
    details: Lorem ipsum dolor sit amet, consectetur adipiscing elit
  - title: Feature C
    details: Lorem ipsum dolor sit amet, consectetur adipiscing elit
---

