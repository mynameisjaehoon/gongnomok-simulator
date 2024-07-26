package site.gongnomok.core.scroll;

import lombok.Getter;
import site.gongnomok.common.enhanceditem.dto.request.EnhanceSuccessDto;
import site.gongnomok.common.exception.EnhancedItemException;
import site.gongnomok.common.exception.ExceptionCode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 주문서 정보를 담고 있는 열거형
 *
 * @author Jaehoon So
 * @version 1.0.0
 */
@Getter
public enum Scroll {
    // GLOVES
    GLOVES_PHY_ATK_10("GLOVES_PHY_ATK", 10, phyAtk(3)),
    GLOVES_PHY_ATK_60("GLOVES_PHY_ATK", 60, phyAtk(2)),
    GLOVES_PHY_ATK_100("GLOVES_PHY_ATK", 100, phyAtk(1)),

    GLOVES_SWIFT_10("GLOVES_SWIFT", 10, acc(5), dex(3), avo(1)),
    GLOVES_SWIFT_60("GLOVES_SWIFT", 60, acc(2), dex(1), avo(1)),
    GLOVES_SWIFT_100("GLOVES_SWIFT", 100, acc(1)),

    GLOVES_HEALTH_10("GLOVES_HEALTH", 10, hp(30)),
    GLOVES_HEALTH_60("GLOVES_HEALTH", 60, hp(15)),
    GLOVES_HEALTH_100("GLOVES_HEALTH", 100, hp(5)),

    // HAT
    HAT_SWIFT_10("HAT_SWIFT", 10, dex(3)),
    HAT_SWIFT_60("HAT_SWIFT", 60, dex(2)),
    HAT_SWIFT_100("HAT_SWIFT", 100, dex(1)),

    HAT_DEFENCE_10("HAT_DEFENCE", 10, phyDef(5), mgDef(3), acc(1)),
    HAT_DEFENCE_60("HAT_DEFENCE", 60, phyDef(2), mgDef(1)),
    HAT_DEFENCE_100("HAT_DEFENCE", 100, phyDef(1)),

    HAT_INTEL_10("HAT_INTEL", 10, intel(3)),
    HAT_INTEL_60("HAT_INTEL", 60, intel(2)),
    HAT_INTEL_100("HAT_INTEL", 100, intel(1)),

    HAT_HEALTH_10("HAT_HEALTH", 10, hp(30)),
    HAT_HEALTH_60("HAT_HEALTH", 60, hp(10)),
    HAT_HEALTH_100("HAT_HEALTH", 100, hp(5)),

    // OVERALL
    OVERALL_SWIFT_10("OVERALL_SWIFT", 10, dex(5), acc(3), move(1)),
    OVERALL_SWIFT_60("OVERALL_SWIFT", 60, dex(2), acc(1)),
    OVERALL_SWIFT_100("OVERALL_SWIFT", 100, dex(1)),

    OVERALL_DEFENCE_10("OVERALL_DEFENCE", 10, phyDef(5), mgDef(3), hp(10)),
    OVERALL_DEFENCE_60("OVERALL_DEFENCE", 60, phyDef(2), mgDef(1)),
    OVERALL_DEFENCE_100("OVERALL_DEFENCE", 100, phyDef(1)),

    OVERALL_INTEL_10("OVERALL_INTEL", 10, intel(5), mgDef(3), mp(10)),
    OVERALL_INTEL_60("OVERALL_INTEL", 60, intel(2), mgDef(1)),
    OVERALL_INTEL_100("OVERALL_INTEL", 100, intel(1)),

    OVERALL_LUCKY_10("OVERALL_LUCKY", 10, luk(5), avo(3), acc(1)),
    OVERALL_LUCKY_60("OVERALL_LUCKY", 60, luk(2), avo(1)),
    OVERALL_LUCKY_100("OVERALL_LUCKY", 100, luk(1)),

    OVERALL_STRENGTH_10("OVERALL_STRENGTH", 10, str(5), phyDef(3), hp(5)),
    OVERALL_STRENGTH_60("OVERALL_STRENGTH", 60, str(2), phyDef(1)),
    OVERALL_STRENGTH_100("OVERALL_STRENGTH", 100, str(1)),

    // TOP
    TOP_DEFENCE_10("TOP_DEFENCE", 10, phyDef(5), mgDef(3), hp(10)),
    TOP_DEFENCE_60("TOP_DEFENCE", 60, phyDef(2), mgDef(1)),
    TOP_DEFENCE_100("TOP_DEFENCE", 100, phyDef(1)),

    TOP_HEALTH_10("TOP_HEALTH", 10, hp(30)),
    TOP_HEALTH_60("TOP_HEALTH", 60, hp(15)),
    TOP_HEALTH_100("TOP_HEALTH", 100, hp(5)),

    TOP_LUCKY_10("TOP_LUCKY", 10, luk(3)),
    TOP_LUCKY_60("TOP_LUCKY", 60, luk(2)),
    TOP_LUCKY_100("TOP_LUCKY", 100, luk(1)),

    TOP_STRENGTH_10("TOP_STRENGTH", 10, str(3)),
    TOP_STRENGTH_60("TOP_STRENGTH", 60, str(2)),
    TOP_STRENGTH_100("TOP_STRENGTH", 100, str(1)),

    // BOTTOM
    BOTTOM_SWIFT_10("BOTTOM_SWIFT", 10, dex(3), acc(2), move(1)),
    BOTTOM_SWIFT_60("BOTTOM_SWIFT", 60, dex(2), acc(1)),
    BOTTOM_SWIFT_100("BOTTOM_SWIFT", 100, dex(1)),

    BOTTOM_DEFENCE_10("BOTTOM_DEFENCE", 10, phyDef(5), mgDef(3), hp(10)),
    BOTTOM_DEFENCE_60("BOTTOM_DEFENCE", 60, phyDef(2), mgDef(1)),
    BOTTOM_DEFENCE_100("BOTTOM_DEFENCE", 100, phyDef(1)),

    BOTTOM_JUMP_10("BOTTOM_JUMP", 10, jump(4), avo(2)),
    BOTTOM_JUMP_60("BOTTOM_JUMP", 60, jump(2), avo(1)),
    BOTTOM_JUMP_100("BOTTOM_JUMP", 100, jump(1)),

    BOTTOM_HEALTH_10("BOTTOM_HEALTH", 10, hp(30)),
    BOTTOM_HEALTH_60("BOTTOM_HEALTH", 60, hp(10)),
    BOTTOM_HEALTH_100("BOTTOM_HEALTH", 100, hp(5)),

    // SHOES
    SHOES_SWIFT_10("SHOES_SWIFT", 10, avo(5), acc(3), move(1)),
    SHOES_SWIFT_60("SHOES_SWIFT", 60, avo(2), acc(1)),
    SHOES_SWIFT_100("SHOES_SWIFT", 100, avo(1)),

    SHOES_MOVE_10("SHOES_MOVE", 10, move(3)),
    SHOES_MOVE_60("SHOES_MOVE", 60, move(2)),
    SHOES_MOVE_100("SHOES_MOVE", 100, move(1)),

    // WEAPONS
    ONE_HANDED_SWORD_PHY_ATK_10("ONE_HANDED_SWORD_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    ONE_HANDED_SWORD_PHY_ATK_60("ONE_HANDED_SWORD_PHY_ATK", 60, phyAtk(2), str(1)),
    ONE_HANDED_SWORD_PHY_ATK_100("ONE_HANDED_SWORD_PHY_ATK", 100, phyAtk(1)),

    ONE_HANDED_SWORD_ACC_10("ONE_HANDED_SWORD_ACC", 10, acc(5), phyAtk(3), dex(3)),
    ONE_HANDED_SWORD_ACC_60("ONE_HANDED_SWORD_ACC", 60, acc(3), phyAtk(1), dex(2)),
    ONE_HANDED_SWORD_ACC_100("ONE_HANDED_SWORD_ACC", 100, acc(1)),

    ONE_HANDED_AXE_PHY_ATK_10("ONE_HANDED_AXE_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    ONE_HANDED_AXE_PHY_ATK_60("ONE_HANDED_AXE_PHY_ATK", 60, phyAtk(2), str(1)),
    ONE_HANDED_AXE_PHY_ATK_100("ONE_HANDED_AXE_PHY_ATK", 100, phyAtk(1)),

    ONE_HANDED_AXE_ACC_10("ONE_HANDED_AXE_ACC", 10, acc(5), phyAtk(3), dex(3)),
    ONE_HANDED_AXE_ACC_60("ONE_HANDED_AXE_ACC", 60, acc(3), phyAtk(1), dex(2)),
    ONE_HANDED_AXE_ACC_100("ONE_HANDED_AXE_ACC", 100, acc(1)),

    ONE_HANDED_BLUNT_PHY_ATK_10("ONE_HANDED_BLUNT_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    ONE_HANDED_BLUNT_PHY_ATK_60("ONE_HANDED_BLUNT_PHY_ATK", 60, phyAtk(2), str(1)),
    ONE_HANDED_BLUNT_PHY_ATK_100("ONE_HANDED_BLUNT_PHY_ATK", 100, phyAtk(1)),

    ONE_HANDED_BLUNT_ACC_10("ONE_HANDED_BLUNT_ACC", 10, acc(5), str(3), phyDef(3)),
    ONE_HANDED_BLUNT_ACC_60("ONE_HANDED_BLUNT_ACC", 60, acc(3), str(1), phyDef(2)),
    ONE_HANDED_BLUNT_ACC_100("ONE_HANDED_BLUNT_ACC", 100, acc(1)),

    TWO_HANDED_SWORD_PHY_ATK_10("TWO_HANDED_SWORD_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    TWO_HANDED_SWORD_PHY_ATK_60("TWO_HANDED_SWORD_PHY_ATK", 60, phyAtk(2), str(1)),
    TWO_HANDED_SWORD_PHY_ATK_100("TWO_HANDED_SWORD_PHY_ATK", 100, phyAtk(1)),

    TWO_HANDED_SWORD_ACC_10("TWO_HANDED_SWORD_ACC", 10, acc(5), phyAtk(3), dex(3)),
    TWO_HANDED_SWORD_ACC_60("TWO_HANDED_SWORD_ACC", 60, acc(3), phyAtk(1), dex(2)),
    TWO_HANDED_SWORD_ACC_100("TWO_HANDED_SWORD_ACC", 100, acc(1)),

    TWO_HANDED_AXE_PHY_ATK_10("TWO_HANDED_AXE_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    TWO_HANDED_AXE_PHY_ATK_60("TWO_HANDED_AXE_PHY_ATK", 60, phyAtk(2), str(1)),
    TWO_HANDED_AXE_PHY_ATK_100("TWO_HANDED_AXE_PHY_ATK", 100, phyAtk(1)),

    TWO_HANDED_AXE_ACC_10("TWO_HANDED_AXE_ACC", 10, acc(5), phyAtk(3), dex(3)),
    TWO_HANDED_AXE_ACC_60("TWO_HANDED_AXE_ACC", 60, acc(3), phyAtk(1), dex(2)),
    TWO_HANDED_AXE_ACC_100("TWO_HANDED_AXE_ACC", 100, acc(1)),

    TWO_HANDED_BLUNT_PHY_ATK_10("TWO_HANDED_BLUNT_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    TWO_HANDED_BLUNT_PHY_ATK_60("TWO_HANDED_BLUNT_PHY_ATK", 60, phyAtk(2), str(1)),
    TWO_HANDED_BLUNT_PHY_ATK_100("TWO_HANDED_BLUNT_PHY_ATK", 100, phyAtk(1)),

    TWO_HANDED_BLUNT_ACC_10("TWO_HANDED_BLUNT_ACC", 10, acc(5), phyAtk(3), dex(3)),
    TWO_HANDED_BLUNT_ACC_60("TWO_HANDED_BLUNT_ACC", 60, acc(3), phyAtk(1), dex(2)),
    TWO_HANDED_BLUNT_ACC_100("TWO_HANDED_BLUNT_ACC", 100, acc(1)),

    SPEAR_PHY_ATK_10("SPEAR_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    SPEAR_PHY_ATK_60("SPEAR_PHY_ATK", 60, phyAtk(2), str(1)),
    SPEAR_PHY_ATK_100("SPEAR_PHY_ATK", 100, phyAtk(1)),

    SPEAR_ACC_10("SPEAR_ACC", 10, acc(5), phyAtk(3), dex(3)),
    SPEAR_ACC_60("SPEAR_ACC", 60, acc(3), phyAtk(1), dex(2)),
    SPEAR_ACC_100("SPEAR_ACC", 100, acc(1)),

    POLEARM_PHY_ATK_10("POLEARM_PHY_ATK", 10, phyAtk(5), str(3), phyDef(1)),
    POLEARM_PHY_ATK_60("POLEARM_PHY_ATK", 60, phyAtk(2), str(1)),
    POLEARM_PHY_ATK_100("POLEARM_PHY_ATK", 100, phyAtk(1)),

    POLEARM_ACC_10("POLEARM_ACC", 10, acc(5), phyAtk(3), dex(3)),
    POLEARM_ACC_60("POLEARM_ACC", 60, acc(3), phyAtk(1), dex(2)),
    POLEARM_ACC_100("POLEARM_ACC", 100, acc(1)),

    CLAW_PHY_ATK_10("CLAW_PHY_ATK", 10, phyAtk(5), acc(3), luk(1)),
    CLAW_PHY_ATK_60("CLAW_PHY_ATK", 60, phyAtk(2), acc(1)),
    CLAW_PHY_ATK_100("CLAW_PHY_ATK", 100, phyAtk(1)),

    // DAGGER
    DAGGER_PHY_ATK_10("DAGGER_PHY_ATK", 10, phyAtk(5), luk(3), phyDef(1)),
    DAGGER_PHY_ATK_60("DAGGER_PHY_ATK", 60, phyAtk(2), luk(1)),
    DAGGER_PHY_ATK_100("DAGGER_PHY_ATK", 100, phyAtk(1)),

    // CROSSBOW
    CROSSBOW_PHY_ATK_10("CROSSBOW_PHY_ATK", 10, phyAtk(5), acc(3), dex(1)),
    CROSSBOW_PHY_ATK_60("CROSSBOW_PHY_ATK", 60, phyAtk(2), acc(1)),
    CROSSBOW_PHY_ATK_100("CROSSBOW_PHY_ATK", 100, phyAtk(1)),

    // WAND
    WAND_MG_ATK_10("WAND_MG_ATK", 10, mgAtk(5), intel(3), mgDef(1)),
    WAND_MG_ATK_60("WAND_MG_ATK", 60, mgAtk(2), intel(1)),
    WAND_MG_ATK_100("WAND_MG_ATK", 100, mgAtk(1)),

    // STAFF
    STAFF_MG_ATK_10("STAFF_MG_ATK", 10, mgAtk(5), intel(3), mgDef(1)),
    STAFF_MG_ATK_60("STAFF_MG_ATK", 60, mgAtk(2), intel(1)),
    STAFF_MG_ATK_100("STAFF_MG_ATK", 100, mgAtk(1)),

    // EARRING
    EARRING_SWIFT_10("EARRING_SWIFT", 10, dex(3)),
    EARRING_SWIFT_60("EARRING_SWIFT", 60, dex(2)),
    EARRING_SWIFT_100("EARRING_SWIFT", 100, dex(1)),

    EARRING_INTEL_10("EARRING_INTEL", 10, mgAtk(5), intel(3), mgDef(1)),
    EARRING_INTEL_60("EARRING_INTEL", 60, mgAtk(2), intel(1)),
    EARRING_INTEL_100("EARRING_INTEL", 100, mgAtk(1)),

    EARRING_LUCKY_10("EARRING_LUCKY", 10, luk(3)),
    EARRING_LUCKY_60("EARRING_LUCKY", 60, luk(2)),
    EARRING_LUCKY_100("EARRING_LUCKY", 100, luk(1)),

    EARRING_HEALTH_10("EARRING_HEALTH", 10, hp(30)),
    EARRING_HEALTH_60("EARRING_HEALTH", 60, hp(15)),
    EARRING_HEALTH_100("EARRING_HEALTH", 100, hp(5)),

    // CAPE
    CAPE_MANA_10("CAPE_MANA", 10, mp(20)),
    CAPE_MANA_60("CAPE_MANA", 60, mp(10)),
    CAPE_MANA_100("CAPE_MANA", 100, mp(5)),

    CAPE_SWIFT_10("CAPE_SWIFT", 10, dex(3)),
    CAPE_SWIFT_60("CAPE_SWIFT", 60, dex(2)),
    CAPE_SWIFT_100("CAPE_SWIFT", 100, dex(1)),

    CAPE_INTEL_10("CAPE_INTEL", 10, intel(3)),
    CAPE_INTEL_60("CAPE_INTEL", 60, intel(2)),
    CAPE_INTEL_100("CAPE_INTEL", 100, intel(1)),

    CAPE_HEALTH_10("CAPE_HEALTH", 10, hp(20)),
    CAPE_HEALTH_60("CAPE_HEALTH", 60, hp(15)),
    CAPE_HEALTH_100("CAPE_HEALTH", 100, hp(5)),

    CAPE_LUCKY_10("CAPE_LUCKY", 10, luk(3)),
    CAPE_LUCKY_60("CAPE_LUCKY", 60, luk(2)),
    CAPE_LUCKY_100("CAPE_LUCKY", 100, luk(1)),

    CAPE_STRENGTH_10("CAPE_STRENGTH", 10, str(3)),
    CAPE_STRENGTH_60("CAPE_STRENGTH", 60, str(2)),
    CAPE_STRENGTH_100("CAPE_STRENGTH", 100, str(1)),

    CAPE_MG_DEF_10("CAPE_MG_DEF", 10, mgDef(5), phyDef(3), mp(10)),
    CAPE_MG_DEF_60("CAPE_MG_DEF", 60, mgDef(3), phyDef(1)),
    CAPE_MG_DEF_100("CAPE_MG_DEF", 100, mgDef(1)),

    CAPE_PHY_DEF_10("CAPE_PHY_DEF", 10, phyDef(5), mgDef(3), hp(10)),
    CAPE_PHY_DEF_60("CAPE_PHY_DEF", 60, phyDef(3), mgDef(1)),
    CAPE_PHY_DEF_100("CAPE_PHY_DEF", 100, phyDef(1)),

    // SHIELD
    SHIELD_DEFENCE_10("SHIELD_DEFENCE", 10, phyDef(5), mgDef(3), hp(10)),
    SHIELD_DEFENCE_60("SHIELD_DEFENCE", 60, phyDef(2), mgDef(1)),
    SHIELD_DEFENCE_100("SHIELD_DEFENCE", 100, phyDef(1)),

    SHIELD_HEALTH_10("SHIELD_HEALTH", 10, hp(30)),
    SHIELD_HEALTH_60("SHIELD_HEALTH", 60, hp(15)),
    SHIELD_HEALTH_100("SHIELD_HEALTH", 100, hp(5)),

    SHIELD_LUCKY_10("SHIELD_LUCKY", 10, luk(3)),
    SHIELD_LUCKY_60("SHIELD_LUCKY", 60, luk(2)),
    SHIELD_LUCKY_100("SHIELD_LUCKY", 100, luk(1)),

    SHIELD_STRENGTH_10("SHIELD_STRENGTH", 10, str(3)),
    SHIELD_STRENGTH_60("SHIELD_STRENGTH", 60, str(2)),
    SHIELD_STRENGTH_100("SHIELD_STRENGTH", 100, str(1));

    private String name; // 주문서 이름
    private int probability;
    private int str = 0;
    private int dex = 0;
    private int intel = 0;
    private int luk = 0;
    private int phyAtk = 0;
    private int mgAtk = 0;
    private int phyDef = 0;
    private int mgDef = 0;
    private int acc = 0;
    private int avo = 0;
    private int move = 0;
    private int jump = 0;
    private int hp = 0;
    private int mp = 0;

    private Scroll(String name, int probability, ScrollAttribute... attributes) {
        this.name = name;
        this.probability = probability;
        for (ScrollAttribute attribute : attributes) {
            attribute.apply(this);
        }
    }

    private interface ScrollAttribute {
        void apply(Scroll scroll);
    }

    private static ScrollAttribute str(int value) {
        return scroll -> scroll.str = value;
    }

    private static ScrollAttribute dex(int value) {
        return scroll -> scroll.dex = value;
    }

    private static ScrollAttribute intel(int value) {
        return scroll -> scroll.intel = value;
    }

    private static ScrollAttribute luk(int value) {
        return scroll -> scroll.luk = value;
    }

    private static ScrollAttribute hp(int value) {
        return scroll -> scroll.hp = value;
    }

    private static ScrollAttribute mp(int value) {
        return scroll -> scroll.mp = value;
    }

    private static ScrollAttribute phyAtk(int value) {
        return scroll -> scroll.phyAtk = value;
    }

    private static ScrollAttribute mgAtk(int value) {
        return scroll -> scroll.mgAtk = value;
    }

    private static ScrollAttribute phyDef(int value) {
        return scroll -> scroll.phyDef = value;
    }

    private static ScrollAttribute mgDef(int value) {
        return scroll -> scroll.mgDef = value;
    }

    private static ScrollAttribute acc(int value) {
        return scroll -> scroll.acc = value;
    }

    private static ScrollAttribute avo(int value) {
        return scroll -> scroll.avo = value;
    }

    private static ScrollAttribute move(int value) {
        return scroll -> scroll.move = value;
    }

    private static ScrollAttribute jump(int value) {
        return scroll -> scroll.jump = value;
    }

    public static List<Scroll> toListFrom(String name) {
        return Arrays.stream(Scroll.values())
            .filter(scroll -> scroll.getName().equals(name))
            .toList();
    }

    public static Map<Integer, Scroll> toProbabilityMapFrom(String name) {
        return Arrays.stream(Scroll.values())
            .filter(scroll -> scroll.getName().equals(name))
            .collect(Collectors.toMap(Scroll::getProbability, scroll -> scroll));
    }

    public static int isEqualWith(
        final ItemStat statCategory,
        final int actualStat,
        final Map<Integer, Scroll> scrolls,
        final EnhanceSuccessDto success
        ) {

        Scroll tenPerScroll = scrolls.get(10);
        Scroll sixtyPerScroll = scrolls.get(60);
        Scroll hundredPerScroll = scrolls.get(100);

        int ten = success.getTen();
        int sixty = success.getSixty();
        int hundred = success.getHundred();

        Integer calculatedStat = switch (statCategory) {
            case STR ->
                tenPerScroll.getStr() * ten + sixtyPerScroll.getStr() * sixty + hundredPerScroll.getStr() * hundred;
            case DEX ->
                tenPerScroll.getDex() * ten + sixtyPerScroll.getDex() * sixty + hundredPerScroll.getDex() * hundred;
            case INT ->
                tenPerScroll.getIntel() * ten + sixtyPerScroll.getIntel() * sixty + hundredPerScroll.getIntel() * hundred;
            case LUK ->
                tenPerScroll.getLuk() * ten + sixtyPerScroll.getLuk() * sixty + hundredPerScroll.getLuk() * hundred;
            case PHY_ATK ->
                tenPerScroll.getPhyAtk() * ten + sixtyPerScroll.getPhyAtk() * sixty + hundredPerScroll.getPhyAtk() * hundred;
            case MG_ATK ->
                tenPerScroll.getMgAtk() * ten + sixtyPerScroll.getMgAtk() * sixty + hundredPerScroll.getMgAtk() * hundred;
            case PHY_DEF ->
                tenPerScroll.getPhyDef() * ten + sixtyPerScroll.getPhyDef() * sixty + hundredPerScroll.getPhyDef() * hundred;
            case MG_DEF ->
                tenPerScroll.getMgDef() * ten + sixtyPerScroll.getMgDef() * sixty + hundredPerScroll.getMgDef() * hundred;
            case ACC ->
                tenPerScroll.getAcc() * ten + sixtyPerScroll.getAcc() * sixty + hundredPerScroll.getAcc() * hundred;
            case AVO ->
                tenPerScroll.getAvo() * ten + sixtyPerScroll.getAvo() * sixty + hundredPerScroll.getAvo() * hundred;
            case MOVE ->
                tenPerScroll.getMove() * ten + sixtyPerScroll.getMove() * sixty + hundredPerScroll.getMove() * hundred;
            case JUMP ->
                tenPerScroll.getJump() * ten + sixtyPerScroll.getJump() * sixty + hundredPerScroll.getJump() * hundred;
            case HP ->
                tenPerScroll.getHp() * ten + sixtyPerScroll.getHp() * sixty + hundredPerScroll.getHp() * hundred;
            case MP ->
                tenPerScroll.getMp() * ten + sixtyPerScroll.getMp() * sixty + hundredPerScroll.getMp() * hundred;
        };

        if (calculatedStat != actualStat) {
            throw new EnhancedItemException(ExceptionCode.INVALID_UPGRADED_STATUS);
        }

        return calculatedStat;
    }

}
