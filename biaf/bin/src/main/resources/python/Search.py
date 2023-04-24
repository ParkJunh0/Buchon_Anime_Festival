def searchstr(strs):
    strb = strs.decode('utf-8')
    stra = strb.replace(',', '')
    strc = stra.split(' ')
    
    return strc