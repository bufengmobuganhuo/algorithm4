package interview.unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1707 {
    public static void main(String[] args) {
        String[] names = {"Fcclu(70)", "Ommjh(63)", "Dnsay(60)", "Qbmk(45)", "Unsb(26)", "Gauuk(75)", "Wzyyim(34)", "Bnea(55)", "Kri(71)", "Qnaakk(76)", "Gnplfi(68)", "Hfp(97)", "Qoi(70)", "Ijveol(46)", "Iidh(64)", "Qiy(26)", "Mcnef(59)", "Hvueqc(91)", "Obcbxb(54)", "Dhe(79)", "Jfq(26)", "Uwjsu(41)", "Wfmspz(39)", "Ebov(96)", "Ofl(72)", "Uvkdpn(71)", "Avcp(41)", "Msyr(9)", "Pgfpma(95)", "Vbp(89)", "Koaak(53)", "Qyqifg(85)", "Dwayf(97)", "Oltadg(95)", "Mwwvj(70)", "Uxf(74)", "Qvjp(6)", "Grqrg(81)", "Naf(3)", "Xjjol(62)", "Ibink(32)", "Qxabri(41)", "Ucqh(51)", "Mtz(72)", "Aeax(82)", "Kxutz(5)", "Qweye(15)", "Ard(82)", "Chycnm(4)", "Hcvcgc(97)", "Knpuq(61)", "Yeekgc(11)", "Ntfr(70)", "Lucf(62)", "Uhsg(23)", "Csh(39)", "Txixz(87)", "Kgabb(80)", "Weusps(79)", "Nuq(61)", "Drzsnw(87)", "Xxmsn(98)", "Onnev(77)", "Owh(64)", "Fpaf(46)", "Hvia(6)", "Kufa(95)", "Chhmx(66)", "Avmzs(39)", "Okwuq(96)", "Hrschk(30)", "Ffwni(67)", "Wpagta(25)", "Npilye(14)", "Axwtno(57)", "Qxkjt(31)", "Dwifi(51)", "Kasgmw(95)", "Vgxj(11)", "Nsgbth(26)", "Nzaz(51)", "Owk(87)", "Yjc(94)", "Hljt(21)", "Jvqg(47)", "Alrksy(69)", "Tlv(95)", "Acohsf(86)", "Qejo(60)", "Gbclj(20)", "Nekuam(17)", "Meutux(64)", "Tuvzkd(85)", "Fvkhz(98)", "Rngl(12)", "Gbkq(77)", "Uzgx(65)", "Ghc(15)", "Qsc(48)", "Siv(47)"};
        String[] synonyms = {"(Gnplfi,Qxabri)", "(Uzgx,Siv)", "(Bnea,Lucf)", "(Qnaakk,Msyr)", "(Grqrg,Gbclj)", "(Uhsg,Qejo)", "(Csh,Wpagta)", "(Xjjol,Lucf)", "(Qoi,Obcbxb)", "(Npilye,Vgxj)", "(Aeax,Ghc)", "(Txixz,Ffwni)", "(Qweye,Qsc)", "(Kri,Tuvzkd)", "(Ommjh,Vbp)", "(Pgfpma,Xxmsn)", "(Uhsg,Csh)", "(Qvjp,Kxutz)", "(Qxkjt,Tlv)", "(Wfmspz,Owk)", "(Dwayf,Chycnm)", "(Iidh,Qvjp)", "(Dnsay,Rngl)", "(Qweye,Tlv)", "(Wzyyim,Kxutz)", "(Hvueqc,Qejo)", "(Tlv,Ghc)", "(Hvia,Fvkhz)", "(Msyr,Owk)", "(Hrschk,Hljt)", "(Owh,Gbclj)", "(Dwifi,Uzgx)", "(Iidh,Fpaf)", "(Iidh,Meutux)", "(Txixz,Ghc)", "(Gbclj,Qsc)", "(Kgabb,Tuvzkd)", "(Uwjsu,Grqrg)", "(Vbp,Dwayf)", "(Xxmsn,Chhmx)", "(Uxf,Uzgx)"};
        System.out.println(Arrays.toString(new Ex1707().trulyMostPopular(names, synonyms)));
    }

    private Map<String, String> ids;

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        ids = new HashMap<>();
        for (String pair : synonyms) {
            int splitIdx = pair.indexOf(',');
            String name1 = pair.substring(1, splitIdx);
            String name2 = pair.substring(splitIdx + 1, pair.length() - 1);
            if (!ids.containsKey(name1)) {
                ids.put(name1, name1);
            }
            if (!ids.containsKey(name2)) {
                ids.put(name2, name2);
            }
            connect(name1, name2);
        }
        Map<String, Integer> countMap = new HashMap<>();
        for (String name : names) {
            int splitIdx = name.indexOf('(');
            String name1 = name.substring(0, splitIdx);
            int count = Integer.parseInt(name.substring(splitIdx + 1, name.length() - 1));
            if (ids.containsKey(name1)) {
                String root = find(name1);
                countMap.put(root, countMap.getOrDefault(root, 0) + count);
            } else {
                countMap.put(name1, count);
            }
        }
        String[] ans = new String[countMap.size()];
        int idx = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            ans[idx] = entry.getKey() + "(" + entry.getValue() + ")";
            idx++;
        }
        return ans;
    }

    private void connect(String point1, String point2) {
        String root1 = find(point1);
        String root2 = find(point2);
        if (root1.equals(root2)) {
            return;
        }
        if (root1.compareTo(root2) < 0) {
            ids.put(root2, root1);
        } else {
            ids.put(root1, root2);
        }
    }

    private String find(String point) {
        while (!point.equals(ids.get(point))) {
            ids.put(point, ids.get(ids.get(point)));
            point = ids.get(point);
        }
        return point;
    }

}
