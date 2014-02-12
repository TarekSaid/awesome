[#ftl]
[#macro under_score field]
${field?replace("(?<=[a-z0-9])[A-Z]|(?<=[a-zA-Z])[0-9]|(?<=[A-Z])[A-Z](?=[a-z])", "_$0", 'r')?upper_case}[/#macro]
